namespace MovieManager
{
    partial class MainForm
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.label1 = new System.Windows.Forms.Label();
            this.button1 = new System.Windows.Forms.Button();
            this.button2 = new System.Windows.Forms.Button();
            this.btnLoad = new System.Windows.Forms.Button();
            this.txtYear = new System.Windows.Forms.TextBox();
            this.btnTime = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("궁서", 36F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.label1.Location = new System.Drawing.Point(22, 392);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(396, 60);
            this.label1.TabIndex = 0;
            this.label1.Text = "무-비 매네쟈";
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(733, 391);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(134, 61);
            this.button1.TabIndex = 1;
            this.button1.Text = "DB 리셋";
            this.button1.UseVisualStyleBackColor = true;
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(593, 391);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(134, 61);
            this.button2.TabIndex = 2;
            this.button2.Text = "업데이트 관리";
            this.button2.UseVisualStyleBackColor = true;
            // 
            // btnLoad
            // 
            this.btnLoad.Location = new System.Drawing.Point(733, 324);
            this.btnLoad.Name = "btnLoad";
            this.btnLoad.Size = new System.Drawing.Size(134, 61);
            this.btnLoad.TabIndex = 3;
            this.btnLoad.Text = "정보 로드";
            this.btnLoad.UseVisualStyleBackColor = true;
            // 
            // txtYear
            // 
            this.txtYear.Location = new System.Drawing.Point(733, 291);
            this.txtYear.Name = "txtYear";
            this.txtYear.Size = new System.Drawing.Size(134, 27);
            this.txtYear.TabIndex = 4;
            // 
            // btnTime
            // 
            this.btnTime.Location = new System.Drawing.Point(487, 262);
            this.btnTime.Name = "btnTime";
            this.btnTime.Size = new System.Drawing.Size(240, 123);
            this.btnTime.TabIndex = 5;
            this.btnTime.Text = "button4";
            this.btnTime.UseVisualStyleBackColor = true;
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(891, 477);
            this.Controls.Add(this.btnTime);
            this.Controls.Add(this.txtYear);
            this.Controls.Add(this.btnLoad);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.label1);
            this.Name = "MainForm";
            this.Text = "무-비 매니저";
            this.Load += new System.EventHandler(this.MainForm_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private Label label1;
        private Button button1;
        private Button button2;
        private Button btnLoad;
        private TextBox txtYear;
        private Button btnTime;
    }
}